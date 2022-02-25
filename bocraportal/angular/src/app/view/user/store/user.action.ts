// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import { UserCriteria } from '@app/model/bw/org/bocra/portal/user/user-criteria';


export enum UserActionType {
    SAVE_USER = '[User] Save User',
    SAVE_USER_SUCCESS = '[User] Save User Success',
    FIND_BY_ID = '[User] Find By Id',
    FIND_BY_ID_SUCCESS = '[User] Find By Id Success',
    LOAD_ALL = '[User] Load All',
    LOAD_ALL_SUCCESS = '[User] Load All Success',
    SEARCH_USERS = '[User] Search Users',
    SEARCH_USERS_SUCCESS = '[User] Search Users Success',
    RESET = '[User] Reset',
    ACTION_FAILURE = '[User] Action Failure'
}

export const saveUser = createAction(
    UserActionType.SAVE_USER,
    props<{ user: UserVO }>()
);

export const saveUserSuccess = createAction(
    UserActionType.SAVE_USER_SUCCESS,
    props<{ user: any }>()
);

export const findById = createAction(
    UserActionType.FIND_BY_ID,
    props<{ id: string }>()
);

export const findByIdSuccess = createAction(
    UserActionType.FIND_BY_ID_SUCCESS,
    props<{ user: UserVO }>()
);

export const loadAll = createAction(
    UserActionType.LOAD_ALL
);

export const loadAllSuccess = createAction(
    UserActionType.LOAD_ALL_SUCCESS,
    props<{ users: UserVO[] }>()
);

export const searchUsers = createAction(
    UserActionType.SEARCH_USERS,
    props<{ searchCriteria: UserCriteria }>()
);

export const searchUsersSuccess = createAction(
    UserActionType.SEARCH_USERS_SUCCESS,
    props<{ users: UserVO[] }>()
);

export const reset = createAction(UserActionType.RESET);

export const userActionFailure = createAction(
    UserActionType.ACTION_FAILURE,
    props<{ error: any }>()
);
