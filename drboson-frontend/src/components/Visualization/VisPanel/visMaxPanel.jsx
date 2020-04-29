import React, { useState } from 'react';
import { ReactSVG } from 'react-svg';
import Modal from 'react-modal';
import x from '../../../images/x.svg';
import './visPanel.css';

const VisMaxPanel = (props) => {
    const maxPanelModalStyles = {
        content: {
            padding: 0,
            // zIndex: 100,
        },
        overlay: {
            backgroundColor: 'rgba(0, 0, 0, 0.85)'
        }
    }

    return (
        <Modal isOpen={props.isMaximized}
            style={maxPanelModalStyles}
            onRequestClose={props.minimizePanel}>

            <div className="panel max-panel">
                <div className="panel__actions">
                    <span onClick={props.minimizePanel} className="panel__actions--action">
                        <ReactSVG src={x} />
                    </span>
                </div>
                <div className="panel__content">
                    <h6 className="panel__content--title">Darko Filipovski</h6>
                    <div className="panel__content--vis">
                        {props.visualization}
                    </div>
                </div>
            </div>
        </Modal>
    );
}

export default VisMaxPanel;