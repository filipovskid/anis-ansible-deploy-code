import React, { useState } from 'react';
import Modal from 'react-modal';
import VisTypeSelector from '../VisTypeSelector/visTypeSelector';


const VisBuilder = (props) => {
    const [isTypeModalOpen, setIsTypeOpen] = useState(true);

    const typeModalStyles = {
        content: {
            width: '740px',
            height: 'auto',
            minHeight: '500px',
            maxHeight: '80vh',
            overflow: 'auto',
            margin: 'auto',
            position: 'relative',
            top: '10%'
        },
        overlay: {
            // display: 'flex',
            // justifyContent: 'center',
            backgroundColor: 'rgba(0, 0, 0, 0.75)'
        }
    }

    return (
        // <Modal isOpen={isTypeModalOpen} style={typeModalStyles}>
        //     <VisTypeSelector />
        // </Modal>
        <Modal isOpen={isTypeModalOpen}>
        </Modal>
    );
}

export default VisBuilder;