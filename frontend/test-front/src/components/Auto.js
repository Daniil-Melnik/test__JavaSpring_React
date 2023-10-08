import React, { useEffect, useState } from 'react';

export default function Auto() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[comand,setComand]=useState('')
    const[discription,setDiscr]=useState('')
    const[students,setStudents]=useState([])

    const handleClick=(e)=>{
        e.preventDefault()
        const student={name, comand, discription, url : "dddd"}
        console.log(student)
        fetch("http://localhost:8080/student/add",{
          method:"POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(student)
    
      }).then(()=>{
        console.log("New Student added")
      })
    }

    useEffect(()=>{
        fetch("http://localhost:8080/student/getAll")
        .then(res=>res.json())
        .then((result)=>{
          setStudents(result);
        }
      )
      },[])
        return (
          <div>
          {/* <h1>Autos</h1>
      
          <div elevation={3} style={paperStyle}>
      
            {students.map(student=>(
              <p style={{margin:"10px",padding:"15px", textAlign:"left"}} key={student.id}>
               Id:{student.id}<br/>
               Name:{student.name}<br/>
               Address:{student.comand}
              </p>
            ))
      }
          </div> */}
            <form noValidate autoComplete="off">
                <input type='text' id="outlined-basic" label="Student Name" variant="outlined" fullWidth 
                value={name}
                onChange={(e)=>setName(e.target.value)}
                />
                <input type='text' id="outlined-basic" label="Student Adress" variant="outlined" fullWidth
                value={comand}
                onChange={(e)=>setComand(e.target.value)}
                />
                <input type='text' id="outlined-basic" label="Student Adress" variant="outlined" fullWidth
                value={discription}
                onChange={(e)=>setDiscr(e.target.value)}
                />
                <button variant="contained" color="secondary" onClick={handleClick}>Submit</button>
            </form>    
          </div>
        );
      }