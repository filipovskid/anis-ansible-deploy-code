import React from 'react';
import Modal from 'react-modal';
import './visConfigurer.css';

const modalStyle = {
    overlay: {
        backgroundColor: 'rgba(0, 0, 0, 0.85)'
    }
}

const VisConfigurer = (props) => {
    return (
        <Modal
            isOpen={props.isOpen}
            onRequestClose={props.onRequestClose}
            ariaHideApp={false}
            style={modalStyle}
        >
            <div className="visualization-modal">
                <div className="visualization-config">
                    <div className="visualization-config__preview">
                        {props.preview}
                    </div>
                    <div className="visualization-config__settings">
                        {props.configurer}
                    </div>
                </div>
                <div className="visualization-actions">
                    <button onClick={props.onRequestClose} type="button" class="btn btn-cancel">Cancel</button>
                    <button type="button" class="btn btn-ok">Save</button>
                </div>
            </div>
        </Modal>

    );
}

export default VisConfigurer;