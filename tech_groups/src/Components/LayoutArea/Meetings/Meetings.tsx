import "./Meetings.css";
import Meeting from "../../../Models/Meeting.ts";
import {useEffect, useState} from "react";
import {getMeetingList, meetingDuration} from "../../../Services/MeetingService.ts";
import {getSingleTechGroup} from "../../../Services/TechGroupService.ts";


interface TechGroupProps {
    selectedTechGroupId: number;
    meetingKey:number;
}


function Meetings({selectedTechGroupId,meetingKey}: TechGroupProps) {
    const [durations, setDurations] = useState<{[key:number]:string}>({});
    const [techGroup, setTechGroup] = useState<string>("");
    const [meetings, setMeetings] = useState<Meeting[]>([]);


    useEffect(() => {
        async function fetchTechGroup() {
                const result = await getSingleTechGroup(selectedTechGroupId);
                if ("groupName" in result) {
                    setTechGroup("your group " + result.groupName);
                }
        }
        fetchTechGroup();
    }, [selectedTechGroupId]);


    useEffect(() => {
        async function fetchMeetings():Promise<void>{
            const result = await getMeetingList(selectedTechGroupId);
            setMeetings(result);
        }
        fetchMeetings();
    }, [selectedTechGroupId,meetingKey]);



    useEffect(() => {
        async function fetchDurations (): Promise<void> {
            let durationMap:{[key:number]:string}={};

            for(const meeting of meetings){
                const result = await meetingDuration(meeting.id);
                if(result){
                    durationMap[meeting.id!] = `${Math.floor(result / 60)}: ${result % 60}`;
                }
            }

            setDurations(durationMap);
        }
        if(meetings.length>0){
            fetchDurations();
        }

    }, [meetings]);

    function beautifyDate(date: string){
        const dateConvert = new Date(date);
        return dateConvert.toLocaleDateString("en-US", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: true,
        });
    }

    return (
        <div className="organize-meetings ">
            <h1>Meeting/s with {techGroup}</h1>
            {meetings != undefined && true && meetings.length > 0 ?
            <table className="meeting-table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Description</th>
                    <th>Room name</th>
                    <th>Meeting duration in hours (Max 10 hours)</th>
                </tr>
                </thead>
                <tbody>
                {meetings.map((meeting) => {
                    return (
                        <tr key={meeting.id}>
                            <td>{meeting.id}</td>
                            <td>{beautifyDate(meeting.startTime)}</td>
                            <td>{beautifyDate(meeting.endTime)}</td>
                            <td>{meeting.description}</td>
                            <td>{meeting.roomName}</td>
                            <td>{durations[meeting.id!] || "Loading..."}</td>
                        </tr>
                    );
                })
                }
                </tbody>
            </table>
                :<p>No available meetings</p>}
        </div>
    );
}

export default Meetings;