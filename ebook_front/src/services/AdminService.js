

export const getUsers=(callback)=>{

    fetch('http://localhost:8080/main/getUsers') // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
           callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const banUser=(params,callback)=>{

    fetch('http://localhost:8080/main/banUser?'+params.toString()) // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
           callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const unbanUser=(params,callback)=>{

    fetch('http://localhost:8080/main/unbanUser?'+params.toString()) // 发送fetch请求获取联系人信息的接口地址
        .then(response => response.json())
        .then(data => {
            callback(data);
        })
        .catch(error => {
            console.error('Error fetching contacts:', error);
        });

}

export const AdminFilterOrderBook=(params,callback)=>{

    fetch('http://localhost:8080/main/AdminFilterOrderBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const AdminFilterOrderDate=(params,callback)=>{

    fetch('http://localhost:8080/main/AdminFilterOrderDate?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const getSale=(params,callback)=>{

    fetch('http://localhost:8080/main/getSale?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })
}

export const getConsumption=(params,callback)=>{

    fetch('http://localhost:8080/main/getConsumption?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })

}

export const getBook=(params,callback)=>{

    fetch('http://localhost:8080/main/getBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        });

}

export const deleteBook=(params,callback)=>{

    fetch('http://localhost:8080/main/deleteBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}
export const editBook=(params,callback)=>{

    fetch('http://localhost:8080/main/editBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);

        })
}

export const addBook=(params,callback)=>{

    fetch('http://localhost:8080/main/addBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })


}