import React from 'react';
import {Button, Descriptions, Input, Table} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import {hadoop_word_count, searchByGraphl, searchByTypeRelate, spark_word_count} from "../services/BookService";


class Mr_graphl extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            graph_book_id:'',
            graph_book:'',
            hadoop:'',
            spark:'',
        };
    }

    handleChange=(event)=>{
        this.setState({graph_book_id:event.target.value});

    }
    handleSearch=(event)=>{

        console.log(this.state.graph_book_id);

        if(this.state.graph_book_id===null){
            return;
        }

        const data = {
            query:`
                query bookDetails($id: String!) {
                  bookById(id: $id) {
                    id
                    image
                    name
                    author
                    type
                    price
                    inventory
                    exist
                    description
                  }
                }
            `,

            variables:{
               id:this.state.graph_book_id
            }

        }

        const callback =  (response) => {
            this.setState({graph_book:response.data.bookById});
        };

        searchByGraphl(data,callback);


    }
    handleHadoop=()=>{

        const callback =  (data) => {
            this.setState({hadoop:data});
        };

        hadoop_word_count(callback);
    }
    handleSpark=()=>{
        const callback =  (data) => {
            this.setState({spark:data});
        };

        spark_word_count(callback);
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
                    <Descriptions title={this.state.graph_book.name} bordered>
                        <Descriptions.Item label={"作者"} span={3}>{this.state.graph_book.author}</Descriptions.Item>
                        <Descriptions.Item label={"分类"} span={3}>{this.state.graph_book.type}</Descriptions.Item>
                        <Descriptions.Item label={"定价"} span={3}>{<span className={"price"}>{'¥' + this.state.graph_book.price}</span>}</Descriptions.Item>

                    </Descriptions>
                </div>

                <div>
                    <Button
                        className="search-btn"
                        style={{ marginRight: -12 }}
                        size="large"
                        type="primary"
                        onClick={this.handleHadoop}
                        icon={<SearchOutlined/>}
                    >
                    </Button>
                    <Descriptions title={"hadoop"} bordered>
                        <Descriptions.Item label={"word count"} span={3}>{this.state.hadoop}</Descriptions.Item>
                    </Descriptions>
                </div>

                <div>
                    <Button
                        className="search-btn"
                        style={{ marginRight: -12 }}
                        size="large"
                        type="primary"
                        onClick={this.handleSpark}
                        icon={<SearchOutlined/>}
                    >
                    </Button>
                    <Descriptions title={"spark"} bordered>
                        <Descriptions.Item label={"word count"} span={3}>{this.state.spark}</Descriptions.Item>
                    </Descriptions>
                </div>



            </div>
        )
    }
}

export default Mr_graphl;