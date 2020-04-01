import React, { useEffect } from 'react'
import { useMouseMove } from './useMouseMove';
import { useTitle } from './useTitle';

const MouseMove = () => {
    const { x, y } = useMouseMove();
    const { changeTitle } = useTitle();

    useEffect(() => {
        changeTitle(`X: ${x}, Y: ${y}`);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [x, y]);

    return (
        <div className="App">
            <center>
                <br />
                <h1>X: {x} </h1>
                <h1>Y: {y} </h1>
            </center>
        </div>
    );
}

export default MouseMove;