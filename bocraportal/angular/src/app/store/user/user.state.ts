// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { UserCriteria } from '@app/model/bw/org/bocra/portal/user/user-criteria';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';

export const userKey = "user";

export interface UserState {
    criteria: UserCriteria | any,
    userId: String | any,
    users: Array<UserVO> | Array<any>,
    id: number | any,
    user: UserVO | any,
    removed: boolean,
    error: any
}

export const initialState: UserState = {
    criteria: null,
    userId: null,
    users: [],
    id: null,
    user: null,
    removed: false,
    error: null
};