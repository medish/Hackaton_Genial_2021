export interface Room {
    number: number,
    departmentId: number,
    capacity: number,
    typeId: number
}

export interface Class {
    id: number,
    duration: Date,
    size: number
};

export interface RoomType {
    id: number,
    name: string
}

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
    hour_begin: number,
    day_id: number,
    room_id: number
};