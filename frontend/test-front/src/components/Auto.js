import React, { useEffect, useState } from 'react';

export default function Auto() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[comand,setComand]=useState('')
    const[discr,setDiscr]=useState('')
    const[students,setStudents]=useState([])

    useEffect(()=>{
        console.log("!!!!fetched!!!!")
        fetch("http://localhost:8080/student/getAll")
        .then(console.log("!!!!fetched_1!!!!"))
        .then(res=>res.json())
        .then((result)=>{
          setStudents(result);
        }
      )
      },[])
        return (
          <div>
          <h1>Autos</h1>
      
          <div elevation={3} style={paperStyle}>
      
            {students.map(student=>(
              <p style={{margin:"10px",padding:"15px", textAlign:"left"}} key={student.id}>
               Id:{student.id}<br/>
               Name:{student.name}<br/>
               Address:{student.comand}
      
              </p>
            ))
      }
      
      
          </div>
      
      
      
          </div>
        );
      }