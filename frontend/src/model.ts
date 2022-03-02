export interface ToDo {
    id: string;
    toDo: string;
    date: string;
    status: Status;
}

export enum Status {
    Open = "Open",
    Done = "Done"
}