import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from "react-router-dom";
import Select from 'react-select';
import DatasetService from '../../../actions/dataset';
import RunService from '../../../actions/run';

const CreateRun = (props) => {
    const [datasets, setDatasets] = useState([]);
    const { projectId } = useParams();
    const history = useHistory()


    useEffect(() => {
        DatasetService.fetchDatasets(projectId)
            .then(response => {
                setDatasets(response.data);
            });
    }, [projectId]);

    const onRunCreate = (e) => {
        e.preventDefault();
        const { name, description, datasetId } = e.target;
        const run = {
            name: name.value,
            description: description.value,
            datasetId: datasetId.value
        }

        RunService.createRun(projectId, run)
            .then(response => {
                history.push(`/${projectId}/info`);
            });
    }

    const datasetOptions = datasets.map(dataset => { return { value: dataset.id, label: dataset.name } })

    return (
        <div className="w-75 mx-auto mt-5" style={{ maxWidth: '740px' }}>
            <h3>Create run</h3>
            <hr />
            <form onSubmit={onRunCreate}>
                <div className="">
                    <div className="form-group">
                        <label htmlFor="name" className="small-font">Name</label>
                        <input type="text" className="form-control col-md-4"
                            id="name"
                            name="name"
                        // value={this.state.name} 
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description" className="small-font">Description<span className="text-muted">(optional)</span></label>
                        <input type="text" className="form-control"
                            id="description"
                            name="description"
                        // value={this.state.description}
                        />
                    </div>
                    <hr />

                    <div className="form-group">
                        <label htmlFor="datasetId" className="small-font">Choose dataset</label>
                        <Select options={datasetOptions} name="datasetId" />
                    </div>
                    <hr />
                    <button type="submit" className="btn btn-primary">Create run</button>
                </div>
            </form >
        </div >
    );

};

export default CreateRun;