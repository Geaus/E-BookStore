import React from 'react';
import {Table, DatePicker, Input, Button, message, Descriptions, Avatar} from 'antd';

import {SearchOutlined} from "@ant-design/icons";
import {getConsumption} from "../../services/AdminService";
const { RangePicker } = DatePicker;

const columns = [

    {
        title: '消费榜排名',
        dataIndex: 'rank',
        key: 'rank',

        render: (rank) => (
            <p>{ 'NO   '+ rank}</p>
        )
    },
    {
        title: '用户头像',
        dataIndex: 'user',
        key: 'ava',

        render: (user) => (

            <div>
                <Avatar size={100} src={user.avatar}/>
            </div>
        )
    },
    {
        title: '用户名称',
        dataIndex: 'user',
        key: 'name',

        render: (user) => (
            <div>
                <p>{user.name}</p>
            </div>
        )
    },

    {
        title: '消费金额',
        dataIndex: 'spend',
        key: 'spend',

        render: (spend) => (
            <div>
                <p>{spend+'  元'}</p>
            </div>
        )
    },


];
class AdminConsume extends React.Component {
    constructor(props) {
        super(props);
        this.state = {

            consume:[],
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
            this.setState({consume:data});
        };

        getConsumption(params,callback);



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
            this.setState({consume:data});
        };

        getConsumption(params,callback);


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


                <Table style={{paddingTop:'50px'}} columns={columns} dataSource={this.state.consume}
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

export default AdminConsume;