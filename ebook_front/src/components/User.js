import React from 'react';
import {Avatar, Button, Card, Descriptions, Form, Input, Layout, Space} from "antd";
import TextArea from "antd/es/input/TextArea";
import {getUser} from "../services/UserService";

class User extends React.Component{

    constructor(props) {
        super(props);
        this.state={
            id:null,
            name:null,
            avatar:null,
            description:null
        }
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({
                id:data.id,
                name:data.name,
                email:data.email,
                avatar:data.avatar,
                description:data.description

            });
        };

        getUser(callback);


    }
    render(){
        console.log(this.state.description)
        return(

            <div className="profile-content" style={{paddingTop:'30px'}}>

                <Card title={'个人信息'} style={{paddingTop:'30px'}}>
                    <Space size={"large"} direction={'vertical'}>

                        <Avatar size={200} src={this.state.avatar} />

                        <Descriptions  bordered>
                            <Descriptions.Item label={"username"} span={3}>{this.state.name}</Descriptions.Item>
                            <Descriptions.Item label={"email"} span={3}>{this.state.email}</Descriptions.Item>
                            <Descriptions.Item label={"description"} span={3}>{this.state.description}</Descriptions.Item>
                        </Descriptions>
                    </Space>
                </Card>

            </div>
        )

    }
}

export default User;