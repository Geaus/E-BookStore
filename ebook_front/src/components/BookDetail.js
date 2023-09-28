import React, {useState,useEffect} from 'react';
import {Descriptions, Button, message} from 'antd';
import { ShoppingCartOutlined} from '@ant-design/icons';
import {useParams} from "react-router-dom";
import {addCart, getBook} from "../services/BookService";
import { useNavigate } from 'react-router-dom';

function BookDetail (){

    const navigate = useNavigate();

    const { bookId } = useParams();
    const [book,setBook] =useState({});

    useEffect(() => {
        getBook(bookId).then(data => setBook(data));
    }, [ bookId ]);

    //  const book = items.find((book) => book.bookId === Number(bookId));

    function handleCart(){

        const uid = sessionStorage.getItem('uid');
        addCart(uid,bookId);

    }

    const handleClick = () => {

        navigate(-1);

    };


    return(
        <div className={"content"}>
            <div className={"book-detail"} style={{paddingBottom:'30px'}}>
                <Button onClick={handleClick} >
                    return
                </Button>

                <div className={"book-image"}><img alt="image" src={book.image} style={{width:"350px", height:"350px"}}/></div>
                <div className={"descriptions"}>



                    <Descriptions title={book.name} bordered>
                        <Descriptions.Item label={"作者"} span={3}>{book.author}</Descriptions.Item>
                        <Descriptions.Item label={"分类"} span={3}>{book.type}</Descriptions.Item>
                        <Descriptions.Item label={"定价"} span={3}>{<span className={"price"}>{'¥' + book.price}</span>}</Descriptions.Item>
                        <Descriptions.Item label={"状态 "} span={3}>{book.inventory !== 0?
                            <span>有货 <span className={"inventory"}>库存{book.inventory}件</span></span> : <span className={"status"}>无货</span>}</Descriptions.Item>
                        <Descriptions.Item label={"作品简介"} span={3}>{book.description}</Descriptions.Item>
                    </Descriptions>
                </div>
            </div>


            <div className={"button-groups"}>
                <Button type="primary"  size={"large"}  ghost onClick={handleCart}>
                    <ShoppingCartOutlined />
                    加入购物车
                </Button>

            </div>
        </div>
    )

}

export default BookDetail;