import {ip} from "../App";


export const getUsers=(callback)=>{

    fetch(ip+'/getUsers') // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
           callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const banUser=(params,callback)=>{

    fetch(ip+'banUser?'+params.toString()) // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
           callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const unbanUser=(params,callback)=>{

    fetch(ip+'/unbanUser?'+params.toString()) // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
            callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const AdminFilterOrderBook=(params,callback)=>{

    fetch(ip+'/AdminFilterOrderBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const AdminFilterOrderDate=(params,callback)=>{

    fetch(ip+'/AdminFilterOrderDate?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const getSale=(params,callback)=>{

    fetch(ip+'/getSale?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const getConsumption=(params,callback)=>{

    fetch(ip+'/getConsumption?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })

}

export const getBook=(params,callback)=>{

    fetch(ip+'/getBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        });

}

export const deleteBook=(params,callback)=>{

    fetch(ip+'/deleteBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}
export const editBook=(params,callback)=>{

    fetch(ip+'/editBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);

        })
}

export const addBook=(params,callback)=>{

    fetch(ip+'/addBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })


}