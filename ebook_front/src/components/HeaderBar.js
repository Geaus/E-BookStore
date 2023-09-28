import React from 'react';
import {Link} from "react-router-dom";

import "../css/Home.css"
import"../css/Header.css"
import {Button, message} from "antd";
import {logout} from "../services/UserService";


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
                          <Button style={{paddingRight:'10px', fontSize:'20px'}} onClick={this.handleLogout}> Log out</Button>
                      </Link>

                  </div>

                </div>
            </div>


        )

    }
}

export default HeaderBar;