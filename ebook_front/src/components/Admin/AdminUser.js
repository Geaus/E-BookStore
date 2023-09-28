import React, { Component } from 'react';
import {Table, Button, Avatar} from 'antd';
import {banUser, getUsers, unbanUser} from "../../services/AdminService";

class AdminUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [], // 存储联系人信息
        };
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({ users: data });
        };

        getUsers(callback);


    }


    handleBan = (record) => {

        const index = record.id;

        console.log(index);

        const params = new URLSearchParams();

        params.append('uid', index);

        const callback =  (data) => {
            this.setState({ users: data });
        };

        banUser(params,callback);


    };

    handleUnban = (record) => {

        const index = record.id;

        const params = new URLSearchParams();

        params.append('uid', index);

        const callback =  (data) => {
            this.setState({ users: data });
        };

        unbanUser(params,callback);

        // fetch('http://localhost:8080/unbanUser?'+params.toString()) // 发送fetch请求获取联系人信息的接口地址
        //     .then(response => response.json())
        //     .then(data => {
        //         this.setState({ users: data });
        //     })
        //     .catch(error => {
        //         console.error('Error fetching contacts:', error);
        //     });
    };


    render() {
        const columns = [
            // 列配置
            {
                title: 'ID',
                dataIndex: 'id',
                key: 'id',
            },
            {
                title: '姓名',
                dataIndex: 'name',
                key: 'name',
            },
            {
                title: '头像',
                dataIndex: 'avatar',
                key: 'avatar',
                render: (text) => <Avatar size={100} src={text}/>,
            },
            {
                title: '操作',
                key: 'action',
                render: (_, record) => {

                    if(record.isBlack.toString() === '1') return (

                        <Button type={"primary"} onClick={() => this.handleUnban(record)}>解封</Button>
                    )

                    else return (

                        <Button onClick={() => this.handleBan(record)}>封禁</Button>
                    )
                }
            },
        ];
        return <Table
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 6,
                }}
                dataSource={this.state.users} columns={columns}
              />;
    }



}


export default AdminUser;