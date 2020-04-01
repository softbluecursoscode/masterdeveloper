import { useState } from "react"

export const useCounter = () => {
    const [ value, setValue] = useState(0);

    const increment = () => setValue(value + 1);

    return { value, increment};
}