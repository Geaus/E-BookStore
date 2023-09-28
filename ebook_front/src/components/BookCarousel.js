import React from 'react';
import {Carousel,Image} from "antd";
import "../css/Home.css"
import book1 from '../assets/carousel/book1.png';
import book2 from '../assets/carousel/book2.png';
import book3 from '../assets/carousel/book3.png';

const contentStyle = {
    height: '160px',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
};
export class BookCarousel extends React.Component{


    render(){


        return (

            <Carousel className={"ant-carousel"}
                      style={{paddingTop:'20px'}}
                      autoplay>

                <div>
                    <img  src={book1} style={contentStyle} alt={"book1"}/>
                </div>
                <div>
                    <img src={book2} style={contentStyle} alt={"book2"}/>
                </div>
                <div>
                    <img src={book3} style={contentStyle} alt={"book3"}/>
                </div>

            </Carousel>

        )

    }
}

export default BookCarousel;