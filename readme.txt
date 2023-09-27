template request

a. Menambahkan Member
- URI: http://localhost:8080/member
- Method: POST
- Data:
{"memberID": "111", "name": "Ahmad","address":
"Jalan Merdeka No. 1","phoneNumber":
"082193223xxx"}
- Headers: Content-Type:application/json
b. Menampilkan semua Member
- URI: http://localhost:8080/member
- Method: GET
c. Mendapatkan data Member berdasarkan ID
- URI: http://localhost:8080/member/1
- Method: GET
d. Mengubah data Member
- URI: http://localhost:8080/member/1
- Method: PUT
- Data:
{"memberID": "111", "name": "Ahmad
Anas","address": "Jalan Merdeka No.
2","phoneNumber": "082193223xxx"}
- Headers: Content-Type:application/json
e. Mengubah nomor telepon Member
- URI: http://localhost:8080/member/1
- Method: PATCH
- Data: {"phoneNumber": "082193223111"}
- Headers: Content-Type:application/json
f. Mencari data Member berdasarkan memberID
- URI:
http://localhost:8080/member/search/findByMember
ID?member_id=111
- Method: GET
g. Mencari data Member berdasarkan nama
- URI:
http://localhost:8080/member/search/findByName?n
ame=Ahmad%20Anas
- Method: GET
h. Menghapus Member
- URI: http://localhost:8080/member/1
- Method: DELETE


URL = http://localhost:8080/return
{
    "memberID": "222",
    "bookID": 5,
    "returnDate": "2023-10-02"
}

URL = http://localhost:8080/borrow
{
    "memberID": "222",
    "bookID": 55,
    "startDate": "2023-09-15",
    "dueDate": "2023-09-30"
}

URL = http://localhost:8080/jsonrpc
getBooks
{
    "jsonrpc": "2.0",
    "method": "getBooks",
    "params": {},
    "id": "2"
}
searchBooks (author or title)
{
    "jsonrpc": "2.0",
    "method": "searchBooks",
    "params": {
        "keyword": "will"
    },
    "id": "1"
}
createBook
{
    "jsonrpc": "2.0",
    "method": "createBook",
    "params": {
        "title": "Belajar Web Service",
        "author": "Budi",
        "description":"Ini adalah
buku web service terbaik"}, "id": "1"
    }

GraphQl

mutation Book {
  createBook(
    title: "Spring Graphql"
    description: "This is Spring Graphql"
    author: "Alan"
  ) {
    id
    title
    description
    author
  }
}
	
query Book {
  books {
    id
    title
    description
    author
  }
}
	
query Book {
  bookById(id: "11") {
    title
    author
  }
}	

mutation Book {
  updateBook(
    id: "10"
    title: "Spring Graphql"
    author: "Mark Alan"
    description: "This is Spring Graphql Book"
  ) {
    id
    title
    author
    description
  }
}
	

mutation Book {
  deleteBook(id: "23") {
    id
  }
}
	
mutation Member {
  createMember(
    memberID: "123"
    name: "alan"
    address: "Alan street"
    phoneNumber: "085622542522"
  ) {
    id
    memberID
    name
    address
    phoneNumber
  }
}
	
query Member {
  members {
    id
    name
    address
  }
}
	
query {
  memberById(id:"2") {
    id
    memberID
    name
  }
}
	
mutation Member{
  updateMember(id: "4", name: "sulthon m.", address: "jl. mulia") {
    id
    memberID
    name
    address
    phoneNumber
  }
}

mutation DeleteMember {
  deleteMember(id: "4") {
    id
    memberID
    name
    address
    phoneNumber
  }
}
	
