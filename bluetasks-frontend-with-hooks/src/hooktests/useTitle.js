import { useEffect, useState } from "react";

export const useTitle = (inititalTitle = document.title) => {
    const [ title, setTitle] = useState(inititalTitle);

    useEffect(() => {
        document.title = title;
    }, [ title ]);

    const changeTitle = (newTitle) => {
        setTitle(newTitle);
    }

    return { title, changeTitle };
}