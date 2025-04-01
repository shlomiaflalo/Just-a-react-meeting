import {Route, Routes} from "react-router-dom";
import SplitScreen from "../SplitScreens/SplitScreen.tsx";


function Routing() {
    return (
        <Routes>
            {/*<Route path="/all-meetings" element={<Meetings />}/>*/}
            {/*<Route path="/add-meeting" element={<TechGroupSide />}/>*/}
            <Route index element={<SplitScreen/>}/>
        </Routes>
    );
}

export default Routing;