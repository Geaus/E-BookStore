import React from 'react';
import {Table, DatePicker, Input, Button, message, Descriptions} from 'antd';
import "../css/Home.css"
import "../css/Header.css"
import {getOrders, statistic} from "../services/BookService";
import {SearchOutlined} from "@ant-design/icons";
const { RangePicker } = DatePicker;

const columns = [
    {
        title: '书籍封面',
        dataIndex: 'book',
        key: 'image',

        render: (book) => (

            <div>
                <img src={book.image} alt={book.name} width="100" height="100" />
            </div>
        )
    },
    {
        title: '图书名称',
        dataIndex: 'book',
        key: 'name',

        render: (book) => (
            <div>
                <p>{book.name}</p>
            </div>
        )
    },

    {
        title: '购买本数',
        dataIndex: 'num',
        key: 'num',

        render: (num) => (
            <div>
                <p>{num+'  本'}</p>
            </div>
        )
    },


];
class Statistic extends React.Component {
    constructor(props) {
        super(props);
        this.state = {

            consume:null,
            startDate:'',
            endDate:''

        };
    }

    componentDidMount() {

        const uid=sessionStorage.getItem('uid');
        const params = new URLSearchParams();
        params.append('uid', uid);
        params.append('start', this.state.startDate);
        params.append('end', this.state.endDate);


        const callback =  (data) => {
            console.log(data)
            this.setState({consume:data});
        };

        statistic(params,callback);



    }


    handleDateChange = (date, dateString) => {

        this.setState({startDate:dateString[0]});
        this.setState({endDate:dateString[1]});
        console.log(dateString[0]);
        console.log(dateString[1]);
    }

    handleDateSearch=()=>{

        console.log(this.state.startDate)
        console.log(this.state.endDate)


        const uid=sessionStorage.getItem('uid');
        const params = new URLSearchParams();
        params.append('uid', uid);
        params.append('start', this.state.startDate);
        params.append('end', this.state.endDate);

        const callback =  (data) => {
            console.log(data)
            this.setState({consume:data});
        };

        statistic(params,callback);


    }
    render() {

        if(this.state.consume==null){
            return ;
        }
        return(
            <div>
                <br/>
                <RangePicker
                    style={{width:'490px'}}
                    size="large" onChange={this.handleDateChange}

                />
                <Button size="large"  type="primary" icon={<SearchOutlined/>}
                        onClick={this.handleDateSearch}
                ></Button>
                <br/>
                <br/>

                <Descriptions  bordered>
                    <Descriptions.Item label={"购买总本数"} span={1}>{this.state.consume.sum_book+'本'}</Descriptions.Item>
                    <Descriptions.Item label={"总金额"} span={1}>{this.state.consume.sum_price+'元'}</Descriptions.Item>
                </Descriptions >

                <Table style={{paddingTop:'50px'}} columns={columns} dataSource={this.state.consume.entrys}
                       pagination={{
                           onChange: page => {
                               console.log(page);
                           },
                           pageSize: 6,
                       }}/>

            </div>

        )
    }
}

export default Statistic;