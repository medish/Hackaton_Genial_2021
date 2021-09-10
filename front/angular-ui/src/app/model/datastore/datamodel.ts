export interface Room {
    number: string,
    department: Department,
    capacity: number,
    roomType: RoomType
};
export interface OurDate{
    dateId:{
        day:string,
        hour:string
    }
    hour:string,
    day:string
}
export interface Lesson {
    id: string,
    duration: string,
    groupSize: number,
    course: Course,
    roomType: RoomType,
    professors: Professor[]
};

export interface Course {
    id: number,
    name: string,
    degrees: Degree[],
    color: string
};


export interface RoomType {
    id: number,
    name: string
};

export interface Professor {
    id: string,
    name: string,
    firstName: string,
    email: string,
    isAdmin: boolean,
};

export interface Department {
    id: number,
    name: string
};

export interface Degree {
    id: string,
    name: string
};

export interface Identity {
    username: string,
    password: string
};

export interface Output {
    date: string,
    room: Room,
    lesson: Lesson
};