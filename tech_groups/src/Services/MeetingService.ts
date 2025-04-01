import axios from "axios";
import appConfig from "../Config/AppConfig";
import Meeting from "../Models/Meeting.ts";

const MEETING: string = appConfig.apiAddress + "/meeting";

export async function getMeetingList(techGroupId: number): Promise<Meeting[]> {
    try {
        const response = await axios.get<Meeting[]>(MEETING + "/" + techGroupId);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }

}

export async function addMeeting(meeting: Meeting, techGroupId: number): Promise<Meeting> {
    try {
        const response = await axios.post<Meeting>(MEETING + "/" + techGroupId, meeting);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }

}

export async function meetingDuration(meetingId: number | undefined): Promise<number> {
    try {
        const response = await axios.get<number>(MEETING + "/duration/" + meetingId);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }


}

