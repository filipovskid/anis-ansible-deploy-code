import React from 'react';
import './runStatus.css';

const RunStatus = (props) => {
    const StatusEnum = Object.freeze({
        PENDING: { value: "Pending", className: "pendind" },
        RUNNING: { value: "Running", className: "running" },
        COMPLETED: { value: "Completed", className: "success" },
        FAILED: { value: "Failed", className: "failed" },
        DEFAULT: { value: "Unknown", className: "default" }
    })
    console.log(props.status)
    console.log(props.status.toUpperCase())

    const status = props.status.toUpperCase() in StatusEnum
        ? StatusEnum[props.status.toUpperCase()]
        : StatusEnum.DEFAULT;

    console.log(status)

    return (
        <div className={`status ${status.className}`}>{status.value}</div>
    );
};

export default RunStatus;