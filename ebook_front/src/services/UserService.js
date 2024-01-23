import {message} from "antd";
import {ip} from "../App";

export const getUser = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch(ip+'/getUser?'+params.toString())

        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
};


export const login = (username, password) => {

    return fetch(ip+'/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${username}&password=${password}`,

        credentials:'include'
    })
        .then((response) => {
            if (response.ok) {

                return response.json();
            } else {
                throw new Error('用户名或密码错误');
            }
        })
        .then((data) => {

            if(data.isBlack.toString()==='1'){
                throw new Error('用户被封禁');
            }
            sessionStorage.setItem('uid', data.id);
            sessionStorage.setItem('type',data.userType)
            return data;
        });
};

export const logout = (callback) => {

   fetch(ip+'/logout',{
       credentials:'include'
   })
       .then(response => response.json())
       .then((data)=>{
           console.log(data);
           message.success(data.result);
       })

};

export const newUser = (params,callback) => {

    fetch(ip+'/newUser?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
};
