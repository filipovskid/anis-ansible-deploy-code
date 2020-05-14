import React, { useState, useEffect } from 'react';
import { useParams, Link } from "react-router-dom";
import { ReactSVG } from 'react-svg';
import OverviewPlane from '../../OverviewPlane/overviewPlane';
import RunItem from '../../Run/RunItem/runItem';
import ProjectService from '../../../actions/project';
import RunService from '../../../actions/run';
import plus from '../../../images/plus.svg';
import './projectInfo.css';


const ProjectInfo = (props) => {
    const [project, setProject] = useState({});
    const [runs, setRuns] = useState([]);
    const { projectId } = useParams();

    useEffect(() => {
        ProjectService.fetchProject(projectId)
            .then(response => {
                const projectData = {
                    name: response.data.name,
                    desc: response.data.description,
                    repo: response.data.repository
                };

                setProject(projectData);
            });
    }, [projectId]);

    useEffect(() => {
        RunService.fetchProjectRuns(projectId)
            .then(response => {
                setRuns(response.data);
            });
    }, [projectId]);

    const overview = {
        heading: project.name,
        desc: project.desc,
        items: [
            { key: "Owner", value: "N/A" },
            { key: "Ceated", value: "N/A" },
            { key: "Repository", value: project.repo },
        ]
    }

    const runItems = runs.map(run => <RunItem run={run} key={run.id} />)

    return (
        <div className="project-info">
            <OverviewPlane overview={overview} />
            <div className="project-info__runs">
                <div className="runs-info">
                    <div className="runs-info__header">
                        <div className="runs-info__header--heading">Runs</div>
                        <div className="runs-info__header--actions">
                            <Link to={`/${projectId}/run/new`}><span className="action"><ReactSVG src={plus} /></span></Link>
                        </div>
                    </div>
                    <div className="runs-info__content">
                        {runItems}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProjectInfo;