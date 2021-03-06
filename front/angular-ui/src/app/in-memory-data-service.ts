import { InMemoryDbService } from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const data = [
        {
            "classes": [
                {
                    "id": 17,
                    "name": "Langages à Objets Avancés"
                },
                {
                    "id": 18,
                    "name": "Programmation Objets Avancés"
                }
        
            ],
            "teachers": [
                {
                    "id": 471,
                    "department_id": 2,
                    "email": "peter.habermehl@irif.fr",
                    "firstName": "Peter",
                    "lastName": "Habermehl",
                    "is_admin": false
                },
                {
                    "id": 472,
                    "department_id": 2,
                    "email": "klimann@irif.fr",
                    "firstName": "Ines",
                    "lastName": "Klimann",
                    "is_admin": true
                }
            ],
            "rooms" : [ {
                "number": "5A",
                "departmentId" : "2",
                "capacity" : "100",
                "typeId": "1"
              }, 
              {
                "number": "5B",
                "departmentId" : "2",
                "capacity" : "75",
                "typeId": "1"
              }
            ]
          }
    ];
    return {data};
  }
}
