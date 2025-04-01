import "./TechGroupSide.css";
import {useForm} from "react-hook-form";
import {SyntheticEvent, useEffect, useState} from "react";
import {getTechGroupList} from "../../../Services/TechGroupService.ts";
import {addMeeting} from "../../../Services/MeetingService.ts";
import Meeting from "../../../Models/Meeting.ts";
import TechGroup from "../../../Models/TechGroup.ts";
import Meetings from "../Meetings/Meetings.tsx";

function TechGroupSide() {

    const [techGroups, setTechGroups] = useState<TechGroup[]>([]);

    const {register, handleSubmit, formState,reset} = useForm<Meeting>();

    const [selectedTechGroup, setSelectedTechGroup] = useState<number>(1);

    //For re-render the meetings table once a new meeting has been added
    const [meetingKey, setMeetingKey] = useState<number>(0);



    function onTechGroupChanged(args: SyntheticEvent): void {
        const select = args.target as HTMLSelectElement;
        const newTechGroupId = Number(select.value);

        if(newTechGroupId !== selectedTechGroup){
            setSelectedTechGroup(newTechGroupId);
        }
    }

    useEffect(() => {
        getTechGroupList().then(data => {
            setTechGroups(data);
        });
    }, []);


    async function send(meeting: Meeting): Promise<void> {
        try{
            await addMeeting(meeting, selectedTechGroup);
            alert(`Meeting has been added successfully`);
            reset();
            setMeetingKey(prev => prev+1);
        }catch(error: any){
            alert(error.message);
        }

    }

    return (
       <>
           <div className="left">
               <Meetings meetingKey={meetingKey} selectedTechGroupId={selectedTechGroup}></Meetings>
           </div>
            <div className="right organize-tech-group">
                <h1>Schedule a new meeting</h1>
                <form onSubmit={handleSubmit(send)} className="organize-tech-group">
                    {
                        techGroups.length > 0 ?
                            <>
                                <div>
                                    <label htmlFor="techGroups">Tech groups  </label>
                                    <select value={selectedTechGroup} onChange={onTechGroupChanged}>
                                        {techGroups.map(techGroup => <option key={techGroup.id} value={techGroup.id}>
                                            {techGroup.groupName}
                                        </option>)}
                                    </select>
                                </div>
                                <div>

                                    <label htmlFor="startTime">Beginning  </label>
                                    <input type="datetime-local" id="startTime"
                                           {...register("startTime", {
                                               required: {value: true, message: 'Required'},
                                           })} />
                                    {formState.errors.startTime && <span>{formState.errors.startTime?.message}</span>}
                                </div>

                                <div>
                                    <label htmlFor="endTime">End  </label>
                                    <input type="datetime-local" id="endTime"
                                           {...register("endTime", {
                                               required: {value: true, message: 'Required'},
                                           })} />
                                    {formState.errors.endTime && <span>{formState.errors.endTime?.message}</span>}
                                </div>

                                <div>
                                    <label htmlFor="description">Description  </label>
                                    <input type="text" id="description" placeholder="Enter a description"
                                           {...register("description", {required: {value: true, message: 'Required'}})} />
                                    {formState.errors.description && <span>{formState.errors.description?.message}</span>}
                                </div>

                                <div>
                                    <label htmlFor="roomName">Room name  </label>
                                    <input type="text" id="roomName" placeholder="Enter your room name"
                                           {...register("roomName", {required: {value: true, message: 'Required'}})} />
                                    {formState.errors.roomName && <span>{formState.errors.roomName?.message}</span>}
                                </div>
                                <button type="submit" className={"buttonChange"}>Add a new meeting</button>
                            </>
                            : <p>Loading data</p>
                    }
                </form>
            </div>
        </>

    );
}

export default TechGroupSide;