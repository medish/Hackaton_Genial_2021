export interface Room {
    number: number,
    departmentId: number,
    capacity: number,
    type: string
};

export interface Class {
    id: number,
    duration: Date,
    group_size: number,
    course: Course,
    roomType: RoomType,
    teachers: Teacher[]
};

export interface CourseDegree {
    course_id: number,
    degree_id: number
};

export interface Course {
    id: number,
    name: string,
    colour: string
};

export interface RoomType {
    id: number,
    name: string
};

export interface Teacher {
    id: number,
    departmentId: number,
    firstName: string,
    lastName: string,
    email: string,
    is_admin: boolean
};

export interface Department {
    id: number,
    name: string
};

export interface Degree {
    id: number,
    name: string
};

export interface Identity {
    username: string,
    password: string
};

export interface Output {
    lesson_id: number,
    date: Date,
    room_id: Room,
    department_id: Department
};