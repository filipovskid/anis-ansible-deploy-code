import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from "react-router-dom";
import OverviewPlane from '../../OverviewPlane/overviewPlane';
import RunStatus from '../RunStatus/runStatus';
import RunService from '../../../actions/run';

const default_run = {
    id: '',
    name: '',
    desc: '',
    status: ''
}

const RunInfo = (props) => {
    const [run, setRun] = useState(default_run);
    const { projectId, runId } = useParams();
    const history = useHistory();

    useEffect(() => {
        RunService.fetchProjectRun(projectId, runId)
            .then(response => {
                const runData = {
                    id: response.data.id,
                    name: response.data.name,
                    desc: response.data.description,
                    status: response.data.status
                }

                setRun(runData);
            })
            .catch(error => {
                history.goBack();
            });
    }, [runId, projectId, history]);

    const overview = {
        heading: run.name,
        desc: run.desc,
        items: [
            { key: "Owner", value: "N/A" },
            { key: "Ceated", value: "N/A" },
            { key: "Status", value: <RunStatus status={run.status} /> },
        ]
    }

    return (
        <div className="run-info">
            <OverviewPlane overview={overview} />
        </div>
    );
}

export default RunInfo;