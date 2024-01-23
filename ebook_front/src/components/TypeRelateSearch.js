import React from 'react';
import {Button, Input, Table} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import {searchByTypeRelate} from "../services/BookService";

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
    {
        title: '类型',
        dataIndex: 'type',
        key: 'type',
    },
];


class TypeRelateSearch extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            type:'',
            books:[],
        };
    }

    handleChange=(event)=>{
        this.setState({type:event.target.value});

    }
    handleSearch=(event)=>{

        console.log(this.state.type);

        if(this.state.type===null){
            return;
        }
        const params = new URLSearchParams();
        params.append('type', this.state.type);

        console.log(this.state.type);
        const callback =  (data) => {
            this.setState({books:data});
        };
        searchByTypeRelate(params,callback);

        console.log(this.state.books)

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

export default TypeRelateSearch;