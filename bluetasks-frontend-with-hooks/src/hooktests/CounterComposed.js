import React, { createContext } from 'react'
import Counter from './Counter'
import { useCounter } from './useCounter';

export const CounterContext = createContext();

const CounterComposed = () => {
    const counter = useCounter();

    return (
        <div className="App">
            <CounterContext.Provider value={counter}>
                <Counter />
                <Counter />
                <Counter />
            </CounterContext.Provider>
        </div>
    );
}

export default CounterComposed;