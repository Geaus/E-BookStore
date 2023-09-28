import React from 'react';
import {Table, DatePicker, Input, Button, message, Descriptions, Modal} from 'antd';
import {SearchOutlined} from "@ant-design/icons";
import {filterBook, getBooks} from "../../services/BookService";
import {addBook, deleteBook, editBook, getBook} from "../../services/AdminService";

class AdminBook extends React.Component {

    constructor(props) {
        super(props);
        this.state = {

            books:[],
            edit_modalIsOpen:false,
            new_modalIsOpen:false,
            bookName:'',

            id:'',
            name:'',
            author:'',
            image:'',
            isbn:'',
            inventory:'',

            type:'',
            price:'',
            description:''

        };
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({books:data});
        };

        getBooks(callback);

        // fetch('http://localhost:8080/getBooks')
        //     .then(response => response.json())
        //     .then((data) => {
        //         console.log(data)
        //         this.setState({books:data});
        //     })

    }

    closeModal1 = () => {
        this.setState({ edit_modalIsOpen: false });
    };
    closeModal2 = () => {
        this.setState({ new_modalIsOpen: false });
    };

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


        // fetch('http://localhost:8080/filterBook?'+params.toString())
        //     .then(response => response.json())
        //     .then((data) => {
        //         this.setState({books:data});
        //     })

    }

    handleEdit=(record)=>{

        const index = record.id;
        console.log(index);
        this.setState({edit_modalIsOpen:true});

        this.setState({id:record.id})


        const params = new URLSearchParams();
        params.append('bid', index);

        const callback =  (data) => {

            this.setState({
                    name:data.name,
                    author:data.author,
                    image:data.image,
                    isbn:data.isbn,
                    inventory:data.inventory,
                    type:data.type,
                    price:data.price,
                    description:data.description
                }
            )
        };

        getBook(params,callback);



    }
    handleNew=()=>{
        this.setState({new_modalIsOpen:true})
    }
    handleDelete=(record)=>{

        const index=record.id;
        const params = new URLSearchParams();
        params.append('bid', index);

        const callback =  (data) => {
            console.log(data)
            this.setState({books:data});
            message.success('删除成功')
        };

        deleteBook(params,callback);



    }

    handleEditSubmit=()=>{

        if(this.state.name===''||this.state.author===''||this.state.isbn===''||
            this.state.inventory===''||this.state.type===''||this.state.price===''||this.state.description===''){
            message.error('所有输入框都不能为空');
            return;
        }
        const params = new URLSearchParams();
        params.append('bid', this.state.id);
        params.append('name', this.state.name);
        params.append('author', this.state.author);
        params.append('image', this.state.image);
        params.append('isbn', this.state.isbn);
        params.append('inventory', this.state.inventory);
        params.append('type', this.state.type);
        params.append('price', this.state.price);
        params.append('description', this.state.description);

        const callback =  (data) => {

            console.log(data)
            this.setState({books:data});
            this.setState({ edit_modalIsOpen: false });

            this.setState({
                edit_modalIsOpen: false ,
                name:'',
                author:'',
                image:'',
                isbn:'',
                inventory:'',
                type:'',
                price:'',
                description:''
            });

            message.success("修改成功");
        };

        editBook(params,callback);



    }

    handleNewSubmit=()=>{

        if(this.state.name===''||this.state.author===''||this.state.isbn===''||
        this.state.inventory===''||this.state.type===''||this.state.price===''||this.state.description===''){
            message.error('所有输入框都不能为空');
            return;
        }
        const params = new URLSearchParams();
        params.append('name', this.state.name);
        params.append('author', this.state.author);
        params.append('image', this.state.image);
        params.append('isbn', this.state.isbn);
        params.append('inventory', this.state.inventory);
        params.append('type', this.state.type);
        params.append('price', this.state.price);
        params.append('description', this.state.description);

        const callback =  (data) => {

            console.log(data)
            this.setState({books:data});
            this.setState({
                new_modalIsOpen: false,
                name:'',
                author:'',
                image:'',
                isbn:'',
                inventory:'',
                type:'',
                price:'',
                description:''
            });

            message.success("添加成功")
        };

        addBook(params,callback);



    }
    render() {

        if(this.state.books==[]){
            return ;
        }

        const columns = [

            {
                title: '封面',
                dataIndex: 'image',
                key: 'image',
                render: (image) => (

                    <div>
                        <img src={image} width="100" height="100" />
                    </div>
                )
            },

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
                title: 'ISBN编号',
                dataIndex: 'isbn',
                key: 'isbn',

            },

            {
                title: '库存量',
                dataIndex: 'inventory',
                key: 'inventory',

                render: (inventory) => (

                   <p>{inventory}</p>
                )
            },

            {
                title: '操作',
                key: 'action',
                render: (_, record) => {

                    return(

                        <div>
                            <Button  onClick={() => this.handleEdit(record)}>修改</Button>

                            <Button onClick={() => this.handleDelete(record)}>删除</Button>
                        </div>
                    )

                }
            },

        ];
        return(
            <div>

                <br/>
                <br/>
                <Button
                    type={"primary"}
                    ghost
                    onClick={this.handleNew}>
                    添加新图书
                </Button>

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

                <Table style={{paddingTop:'20px'}} columns={columns} dataSource={this.state.books}
                       pagination={{
                           onChange: page => {
                               console.log(page);
                           },
                           pageSize: 6,
                       }}/>

                <Modal open={this.state.edit_modalIsOpen} onOk={this.handleEditSubmit} onCancel={this.closeModal1}>

                       <label>书名</label>
                      <Input
                          value={this.state.name}
                          onChange={(e)=>{
                          this.setState({name:e.target.value});console.log(this.state.name)
                      }}/>

                    <label>作者</label>
                    <Input
                        value={this.state.author}
                        onChange={(e)=>{
                            this.setState({author:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>封面</label>
                    <Input
                        value={this.state.image}
                        onChange={(e)=>{
                            this.setState({image:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>ISBN</label>
                    <Input
                        value={this.state.isbn}
                        onChange={(e)=>{
                            this.setState({isbn:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>库存</label>
                    <Input
                        value={this.state.inventory}
                        onChange={(e)=>{
                            this.setState({inventory:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>类型</label>
                    <Input
                        value={this.state.type}
                        onChange={(e)=>{
                            this.setState({type:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>价格</label>
                    <Input
                        value={this.state.price}
                        onChange={(e)=>{
                            this.setState({price:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>描述</label>
                    <Input
                        value={this.state.description}
                        onChange={(e)=>{
                            this.setState({description:e.target.value});console.log(this.state.name)
                        }}/>



                </Modal>



                <Modal open={this.state.new_modalIsOpen} onOk={this.handleNewSubmit} onCancel={this.closeModal2}>

                    <label>书名</label>
                    <Input
                        value={this.state.name}
                        onChange={(e)=>{
                            this.setState({name:e.target.value});console.log(this.state.name)
                        }}/>

                    <label>作者</label>
                    <Input
                        value={this.state.author}
                        onChange={(e)=>{
                            this.setState({author:e.target.value});
                        }}/>

                    <label>封面</label>
                    <Input
                        value={this.state.image}
                        onChange={(e)=>{
                            this.setState({image:e.target.value});
                        }}/>

                    <label>ISBN</label>
                    <Input
                        value={this.state.isbn}
                        onChange={(e)=>{
                            this.setState({isbn:e.target.value});
                        }}/>

                    <label>库存</label>
                    <Input
                        value={this.state.inventory}
                        onChange={(e)=>{
                            this.setState({inventory:e.target.value});
                        }}/>

                    <label>类型</label>
                    <Input
                        value={this.state.type}
                        onChange={(e)=>{
                            this.setState({type:e.target.value});
                        }}/>

                    <label>价格</label>
                    <Input
                        value={this.state.price}
                        onChange={(e)=>{
                            this.setState({price:e.target.value});
                        }}/>

                    <label>描述</label>
                    <Input
                        value={this.state.description}
                        onChange={(e)=>{
                            this.setState({description:e.target.value});
                        }}/>

                </Modal>

            </div>

        )
    }
}

export default AdminBook;