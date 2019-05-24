import React from 'react'

class TodoCreator extends React.Component
{
    state = {text: ''}

    render() {
        const { text } = this.state
        const { submit } = this.props

        return <>
            <div>
                <input type="text" onChange={(e) => this.setState({text: e.target.value})} value={text} />
            </div>
            <div>
                <button onClick={() => submit({text: text, isDone: false})}>Create</button>
            </div>
        </>
    }
}


export default TodoCreator
