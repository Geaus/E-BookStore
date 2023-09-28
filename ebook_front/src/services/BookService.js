import {message} from "antd";
export function getBook(bookId) {
    return fetch(`http://localhost:8080/getBook/${bookId}`).then((response) =>
        response.json()
    );
}

export const getBooks = (callback) => {

    fetch('http://localhost:8080/getBooks')
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })
};


export const addCart = (uid,bookid) => {

    const params = new URLSearchParams();
    params.append('uid', uid);
    params.append('bookid', bookid);

      fetch(`http://localhost:8080/addCart?`+params.toString())

         .then(response=>{
            if(response.ok){
                message.success("购物车添加成功");

            }
            else{
                message.error("购物车添加失败");
            }});
};


export const clearCart = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch('http://localhost:8080/makeOrder?'+params.toString()).then(response => response.json())

        .then((data) => {

               if(data.toString()==='1'){
                   message.success('订单确认成功');
               }
               else if(data.toString()==='0'){
                   message.error('购物车为空或购物车中书籍全部下架')
               }


        });

};


export const getCart = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch('http://localhost:8080/getCarts?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        });
};

export const getOrders = (callback) => {

    const uid = sessionStorage.getItem('uid');
    const params = new URLSearchParams();
    params.append('uid', uid);

    fetch('http://localhost:8080/getOrders?'+params.toString())
        .then((response) => response.json())
        .then((data) => {
            callback(data);
        });
};

export const statistic=(params,callback)=>{

    fetch('http://localhost:8080/statistic?'+params.toString())
        .then(response => response.json())
        .then((data) => {
          callback(data);
        })
}

export const UserFilterOrderDate=(params,callback)=>{

    fetch('http://localhost:8080/UserFilterOrderDate?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const UserFilterOrderBook=(params,callback)=>{

    fetch('http://localhost:8080/UserFilterOrderBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
           callback(data);
        })

}

export const filterBook=(params,callback)=>{

    fetch('http://localhost:8080/filterBook?'+params.toString())
        .then(response => response.json())
        .then((data) => {
            callback(data);
        })

}