import axios from "axios";
import appConfig from "../Config/AppConfig";
import TechGroup from "../Models/TechGroup.ts";

const TECH_GROUP: string = appConfig.apiAddress + "/techGroup";

export async function getTechGroupList(): Promise<TechGroup[]> {
    try {
        const response = await axios.get<TechGroup[]>(TECH_GROUP);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }

}

export async function getSingleTechGroup(techGroupId: number): Promise<TechGroup | Error> {
    try {
        const response = await axios.get<TechGroup>(TECH_GROUP + "/" + techGroupId);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }
}

export async function addTechGroup(techGroup: TechGroup): Promise<TechGroup | Error> {
    try {
        const response = await axios.post<TechGroup>(TECH_GROUP, techGroup);
        return response.data;
    } catch (error: any) {
        throw new Error(
            error.response?.data?.message
        );
    }
}
