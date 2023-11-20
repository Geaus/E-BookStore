import React from 'react';
import {Button, Input, Table} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import {getAuthor} from "../services/BookService";

const columns = [
    {
        title: '书名',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '作者',
        dataIndex: 'author',
        key: 'author',
    },
];


class Microservice extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            name:'',
            books:[]
        };
    }

    handleChange=(event)=>{
        this.setState({name:event.target.value});

    }
    handleSearch=(event)=>{

        console.log(this.state.name);

        if(this.state.name===null){
            return;
        }
        const params = new URLSearchParams();
        params.append('name', this.state.name);

        const callback =  (data) => {
            this.setState({books:data});
        };
        getAuthor(params,callback);

    }
    render(){
        return(
            <div>
                <div>
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
                        value={this.state.bookName}
                        onChange={this.handleChange}
                        style={{width:'530px',height:'40px'}}
                    />
                </div>

                <div>
                    <Table style={{paddingTop:'30px'}} columns={columns} dataSource={this.state.books}/>
                </div>

            </div>
        )
    }
}

export default Microservice;