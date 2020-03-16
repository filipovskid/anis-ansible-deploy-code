import React from 'react';
import './profileSidebar.css';

const ProjectSidebar = (props) => {
    return (
        <div class="profile col-lg-3 col-md-4 col-12 pr-md-5 px-3 py-2" style={{ backgroundColor: "white" }} >
            <div class="row justify-content-start">
                <div class="col-md-12 col-3">
                    <div class="profile-avatar">
                        <img alt="" width="260" height="260"
                            src="https://avatars1.githubusercontent.com/u/37289276" />
                    </div>
                </div>
                <div class="profile-info col-md-12 col-9 mt-md-2 ml-md-0 ml-n3 py-1">
                    <span class="profile-info__fullname d-block">Darko Filipovski</span>
                    <span class="profile-info__username d-block">filipovskid</span>
                </div>
            </div>
        </div >
    );
}

export default ProjectSidebar;