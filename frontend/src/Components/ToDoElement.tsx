interface ToDoElementProps{
    toDo: string,
    deadLineDate: string
}

export default function ToDoElement (props: ToDoElementProps) {
    return (
        <div>
            <h1>Was ist zu tun?</h1>
            <p>Was: {props.toDo}</p>
            <p>Bis wann: {props.deadLineDate}</p>
        </div>
    )

}