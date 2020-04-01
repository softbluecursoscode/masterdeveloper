import React from 'react'

const Spinner = () =>
    <div className="d-flex justify-content-center">
        <div className="spinner-border" role="status">
            <span className="sr-only">Carregando...</span>
        </div>
    </div>

export default Spinner;