import {message} from "antd";
export function getBook(bookId) {
    return fetch(`http://localhost:8080/main/getBook/${bookId}`).then((response) =>
        response.json()
    );
}

export const getBooks = (callback) => {

    fetch('http://localhost:8080/main/getBooks')
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

      fetch(`http://localhost:8080/main/addCart?`+params.toString())

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

    fetch('http://localhost:8080/main/makeOrder?'+params.toString()).then()

};


export const getCart = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch('http://localhost:8080/main/getCarts?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        });
};

export const getOrders = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch('http://localhost:8080/main/getOrders?'+params.toString())
        .then((response) => response.json())
        .then((data) => {
            callback(data);
        });
};

export const statistic=(params,callback)=>{

    fetch('http://localhost:8080/main/statistic?'+params.toString())
        .then(response => response.json())
        .then((data) => {
          callback(data);
        })
}

export const UserFilterOrderDate=(params,callback)=>{

    fetch('http://localhost:8080/main/UserFilterOrderDate?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const UserFilterOrderBook=(params,callback)=>{

    fetch('http://localhost:8080/main/UserFilterOrderBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const filterBook=(params,callback)=>{

    fetch('http://localhost:8080/main/filterBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })

}