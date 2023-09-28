import React from 'react';
import BookCard from "./BookCard";
import {Button, Input, List, message} from "antd";
import '../css/Home.css'
import {filterBook, getBooks} from "../services/BookService";
import {SearchOutlined} from "@ant-design/icons";
import BookCarousel from "./BookCarousel";


class BookList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            bookName:'',
            books:[]
        };
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({books:data});
        };

        getBooks(callback);

    }

    handleChange=(event)=>{
        this.setState({bookName:event.target.value});

    }
    handleSearch=(event)=>{

        console.log(this.state.bookName);

        if(this.state.bookName===null){
            return;
        }
        const params = new URLSearchParams();
        params.append('bookName', this.state.bookName);

        const callback =  (data) => {
            this.setState({books:data});
        };

        filterBook(params,callback);


    }

    render(){
        return(
            <div>

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
                        value={this.state.bookName}
                        onChange={this.handleChange}
                        style={{width:'530px',height:'40px'}}
                    />

                </div>

                <BookCarousel/>

                <List
                    grid={{ gutter: 10, column: 3 }}
                    dataSource={this.state.books}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 6,
                    }}
                    renderItem={(item) => (

                        <List.Item>
                            <BookCard info={item} />
                        </List.Item>
                    )}
                />

            </div>
        )
    }
}

export default BookList;