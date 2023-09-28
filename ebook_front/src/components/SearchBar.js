import React from 'react';
import {Button, Input} from "antd";
import { SearchOutlined } from '@ant-design/icons';
import "../css/Home.css"
const { Search } = Input;


export class SearchBar extends React.Component{

    constructor(props) {
        super(props);
        this.state = {data:null};
    }

    handleChange=(event)=>{
        this.setState({data:event.target.value});

    }
    handleSearch=(event)=>{

        console.log(this.state.data);
    }

    render(){


        return(
            <div className="global-search-wrapper" style={{ width: "530px" ,paddingTop:"20px"}}>

                <Input
                    suffix={
                        <Button
                            className="search-btn"
                            style={{ marginRight: -12 }}
                            size="large"
                            type="primary"
                            onClick={this.handleSearch}
                            icon={<SearchOutlined/>}
                        >
                        </Button>
                    }
                    value={this.state.data}
                    onChange={this.handleChange}
                />

            </div>
        )

    }
}

export default SearchBar;