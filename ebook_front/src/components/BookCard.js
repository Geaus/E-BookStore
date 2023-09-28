import React from 'react';
import {Card} from "antd";
import {Link} from 'react-router-dom'
import "../css/Home.css"
const { Meta } = Card;

class BookCard extends React.Component{

    render(){
        const{info}=this.props;
        if(!info) return null;
        const {id,image,name,author,type,price,inventory,description} = info;
        return(

            <Link to= {`/bookDetails/${info.id}`}
            >
              <Card className={'ant-card'}
                  hoverable
                  bordered
                  style={{width: "180px"}}
                  cover={<img alt="image" src={info.image} className={"bookImg"}/>}
              >
                  <Meta title={info.name} description={info.author}/>
                  <Meta title={'ISBN: ' + info.isbn} description={'库存' + info.inventory}/>
              </Card>
             </Link>
        )

    }
}

export default BookCard;