import React from 'react';
import {Table, DatePicker, Input, Button, message, Descriptions} from 'antd';

import {SearchOutlined} from "@ant-design/icons";
import {getSale} from "../../services/AdminService";
const { RangePicker } = DatePicker;

const columns = [

    {
        title: '热销榜排名',
        dataIndex: 'rank',
        key: 'rank',

        render: (rank) => (

            <p>{ 'NO   '+ rank}</p>
        )
    },
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
        title: '书籍名称',
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
                <p>{num + '  本'}</p>
            </div>
        )
    },


];
class AdminSale extends React.Component {
    constructor(props) {
        super(props);
        this.state = {

            sale:[],
            startDate:'',
            endDate:''

        };
    }

    componentDidMount() {

        const params = new URLSearchParams();
        params.append('start', this.state.startDate);
        params.append('end', this.state.endDate);

        const callback =  (data) => {

            console.log(data)
            this.setState({sale:data});

        };

        getSale(params,callback);



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

        const params = new URLSearchParams();
        params.append('start', this.state.startDate);
        params.append('end', this.state.endDate);

        const callback =  (data) => {
            console.log(data)
            this.setState({sale:data});

        };

        getSale(params,callback);


    }
    render() {


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


                <Table style={{paddingTop:'50px'}} columns={columns} dataSource={this.state.sale}
                       pagination={{
                           onChange: page => {
                               console.log(page);
                           },
                           pageSize: 6,
                       }}
                />

            </div>

        )
    }
}

export default AdminSale;