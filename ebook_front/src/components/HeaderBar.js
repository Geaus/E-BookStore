import React from 'react';
import {Link} from "react-router-dom";

import "../css/Home.css"
import"../css/Header.css"
import {Button, message} from "antd";
import {logout} from "../services/UserService";
import {LogoutOutlined} from "@ant-design/icons";


class HeaderBar extends React.Component{

   handleLogout=()=>{

       logout();

    }

    render(){
        return(

            <div id="header">
                <div id="header-content">

                    <div className="logo">
                        Book Store
                    </div>

                  <div className="login">
                      <Link to={"/"}>
                          <Button
                                  onClick={this.handleLogout}
                                  icon={<LogoutOutlined/>}>
                          </Button>
                      </Link>

                  </div>

                </div>
            </div>


        )

    }
}

export default HeaderBar;