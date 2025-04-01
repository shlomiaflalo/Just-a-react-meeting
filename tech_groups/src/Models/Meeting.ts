import TechGroup from "./TechGroup.ts";

interface Meeting {
    id?: number,
    techGroup: TechGroup,
    startTime: string,
    endTime: string,
    description: string,
    roomName: string
}

export default Meeting;