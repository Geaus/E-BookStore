import React from 'react';
import {Button, Layout, message, Table} from 'antd';
import {clearCart, getCart} from "../services/BookService";
import {Link} from "react-router-dom";




// 定义列的配置
const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: '购物车详情',
        dataIndex: 'book',
        key: 'book',
        render: (book) => (
            <div>
                <img src={book.image} alt={book.name} width="100" height="100" />
                <p>图书名称:{book.name}</p>
                {book.exist.toString()==='0'&&(
                    <p>该书已经被下架了！</p>
                )}

            </div>
        )
    },
    {
        title: '数量',
        dataIndex: 'num',
        key: 'num',
    }

];


class Cart extends React.Component{

    constructor(props) {
        super(props);
        this.state = {cart:[]};
    }

    componentDidMount() {
        const callback =  (data) => {
            this.setState({cart:data});
        };

        getCart(callback);


    }

    handleClear=()=>{

        const callback =  () => {
            this.setState({cart:[]});
        };

        clearCart(callback);
        this.setState({cart:[]})

    }
    render() {
        return(

            <Layout>
                <content>
                    <Table style={{paddingTop:'50px'}} columns={columns} dataSource={this.state.cart}/>

                        <Button type="primary"  size={"large"}  ghost onClick={this.handleClear} >清空购物车</Button>

                </content>
            </Layout>

        );
    }
}
export default Cart;