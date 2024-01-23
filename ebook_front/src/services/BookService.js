import {message} from "antd";
import {ip} from "../App";
export function getBook(bookId) {
    return fetch(ip+`/getBook/${bookId}`).then((response) =>
        response.json()
    );
}

export const getBooks = (callback) => {

    fetch(ip+'/getBooks')
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
};

export const getAuthor = (params,callback) => {

    fetch('http://localhost:8080/micro/searchAuthor?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
};


export const addCart = (uid,bookid) => {

    const params = new URLSearchParams();
    params.append('uid', uid);
    params.append('bookid', bookid);

      fetch(ip+`/addCart?`+params.toString())

         .then(response=>{
            if(response.ok){
                message.success("购物车添加成功");

            }
            else{
                message.error("购物车添加失败");
            }});
};


export const makeOrder = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch(ip+'/makeOrder?'+params.toString()).then()

};


export const getCart = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch(ip+'/getCarts?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        });
};

export const getOrders = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch(ip+'/getOrders?'+params.toString())
        .then((response) => response.json())
        .then((data) => {
            callback(data);
        });
};

export const statistic=(params,callback)=>{

    fetch(ip+'/statistic?'+params.toString())
        .then(response => response.json())
        .then((data) => {
          callback(data);
        })
}

export const UserFilterOrderDate=(params,callback)=>{

    fetch(ip+'/UserFilterOrderDate?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const UserFilterOrderBook=(params,callback)=>{

    fetch(ip+'/UserFilterOrderBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const filterBook=(params,callback)=>{

    fetch(ip+'/filterBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })

}

export const searchByTypeRelate=(params,callback)=>{

    fetch(ip+'/neo4j?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}
export const searchByGraphl=(data,callback)=>{

    fetch(ip+'/graphql',{
        method: 'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body: JSON.stringify(data),
        credentials:'include'
    })
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}
export const hadoop_word_count=(callback)=>{

    fetch(ip+'/hadoop')
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}
export const spark_word_count=(callback)=>{

    fetch(ip+'/spark')
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
}