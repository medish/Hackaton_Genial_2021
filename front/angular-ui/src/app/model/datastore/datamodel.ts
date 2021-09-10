export interface Room {
    number: number,
    departmentId: number,
    capacity: number,
    type: string
};

export interface Lesson {
    id: number,
    duration: string,
    group_size: number,
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
    id: number,
    name: string,
    firstName: string,
    email: string,
    is_admin: boolean,
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
    date: string,
    room: Room,
    lesson: Lesson
};