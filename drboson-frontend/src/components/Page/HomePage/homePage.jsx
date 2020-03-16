import React from 'react';
import ProfileSidebar from '../../User/ProfileSidebar/profileSidebar';
import ProjectContainer from '../../Projects/ProjectContainer/projectContainer';

const HomePage = (props) => {
    return (
        <div className="content row">
            <ProfileSidebar />
            <div className="user-assets col-lg-9 col-md-8 col-12 pl-md-4 mt-md-4 p-3">
                <ProjectContainer />
            </div>
        </div>
    );
}

export default HomePage;